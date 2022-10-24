from cryptography.hazmat.primitives import hashes
from flask import Blueprint,render_template,request,flash,redirect,url_for
from .forms import RequestResetForm,ResetPasswordForm
from .models import User
from . import db
from werkzeug.security import generate_password_hash
from flask_login import login_user,login_required,logout_user,current_user
from .__init__ import Mail
from flask_mail import Message
from flask import current_app,session
import re
from cryptography.fernet import Fernet
import base64
import hashlib
import urllib.request

auth = Blueprint('auth',__name__)

@auth.route ('/login',methods=['GET','POST'])
def login():
    try:
        if urllib.request.urlopen("http://127.0.0.1:5002/login").getcode()==200:
            return redirect("http://127.0.0.1:5002/login")
    except Exception as e :
        flash ('Error'+str(e),category='error')
        flash('Please start UAP', category='error')
        return redirect(url_for('auth.register'))
    


@auth.route ('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('auth.login'))

@auth.route ('/register',methods=['GET','POST'])
def register():
    if request.method=='POST':
            email=request.form.get('email')
            full_name=request.form.get('fullname')
            password=request.form.get('password')
            repassword=request.form.get('repassword')

            user=User.query.filter_by(email=email).first()
            has_symbol = False
            for c in '~!@#$%^&*()_+=-`':    
                if c in password:
                    has_symbol = True
                    break
            if user:
                flash('Email already exists.',category='error')
            elif len(email) < 4:
                flash('Email must be greater than 3 characters.', category='error')
            elif len(full_name) < 2:
                flash('First name must be greater than 1 character.', category='error')
            elif password != repassword:
                flash('Passwords don\'t match.', category='error')
            elif len(password) < 10:
                flash('Password must be at least 10 characters.', category='error')
            elif re.search('[0-9]',password) is None:
                flash('Make sure your password has a number in it', category='error')
            elif re.search('[A-Z]',password) is None:
                flash('Make sure your password has a uppercase letter in it', category='error')
            elif not has_symbol:
                flash('Make sure your password has a symbol in it', category='error')
            else:
                if email.find('/'): email.replace('/','\'')
                if email.find('-'): email.replace('-','\-')
                if email.find('"'): email.replace('-','\"')
                if full_name.find('/'): full_name.replace('/','\'')
                if full_name.find('-'): full_name.replace('-','\-')
                if full_name.find('"'): full_name.replace('-','\"')
                if password.find('/'): password.replace('/','\'')
                if password.find('-'): password.replace('-','\-')
                if password.find('"'): password.replace('-','\"')

                passencrypt = password.encode()
                pass1='pass'
                key = hashlib.md5(pass1.encode()).hexdigest()
                kkey = base64.urlsafe_b64encode(key.encode())
                f = Fernet(kkey)
                encrypted = f.encrypt(passencrypt)


                user = User(email=email,full_name=full_name,password=encrypted)
                db.session.add(user)
                db.session.commit()
                login_user(user,remember=True)
                flash('Account created!',category='success')
                return redirect(url_for('views.home', user = current_user))
  
    return render_template("register.html",user=current_user)

@auth.route ('/profile',methods=['GET','POST'])
def profile():
    username=None
    username=session['username']
    if username!=None:
        user=User.query.filter_by(email=username).first()
        if user:
            login_user(user,remember=True)
            return render_template('profile.html',user=user)
    if not current_user.is_authenticated:
        return redirect(url_for('auth.register'))
    return render_template("profile.html",user=current_user)

def send_reset_email(user):
    mail=Mail(current_app)
    token = user.get_reset_token()
    msg = Message('Password Reset Request', sender='forumsendergyyym@gmail.com', recipients=[user.email])
    msg.body = f'''To reset your password, visit the following link:
{url_for('auth.reset_token', token=token, _external=True)}

If you did not make this request then simply ignore this email and no changes will be made.
'''
    mail.send(msg)

@auth.route ('/resetpassword',methods=['GET','POST'])
def reset_request():
    if current_user.is_authenticated:
        return redirect(url_for('views.home'))
    form = RequestResetForm()
    if form.validate_on_submit():
        user = User.query.filter_by(email=form.email.data).first()
        send_reset_email(user)
        flash('An email has been sent with instructions to reset your password.',category='sucess')
        return redirect(url_for('auth.login'))
    return render_template('reset_request.html',title='Reset Password',form=form,user=current_user)

@auth.route ('/resetpassword/<token>',methods=['GET','POST'])
def reset_token(token):
    if current_user.is_authenticated:
        return redirect(url_for('views.home'))
    user = User.verify_reset_token(token)
    if user is None:
        flash('That is an invalid or expired token',category='error')
        return redirect(url_for('auth.reset_request',user=current_user))
    form = ResetPasswordForm()
    if form.validate_on_submit():
        password=request.form.get('password')
        has_symbol = False
        for c in '~!@#$%^&*()_+=-`':    
            if c in password:
                has_symbol = True
                break
        
        if password.find('/'): password.replace('/','\'')
        if password.find('-'): password.replace('-','\-')
        if password.find('"'): password.replace('-','\"')
        
        if len(password) < 10:
            flash('Password must be at least 10 characters.', category='error')
        if re.search('[0-9]',password) is None:
            flash('Make sure your password has a number in it', category='error')
        if re.search('[A-Z]',password) is None:
            flash('Make sure your password has a uppercase letter in it', category='error')
        if not has_symbol:
            flash('Make sure your password has a symbol in it', category='error')
        else:
            hashed_password = generate_password_hash(password,method='sha256')
            user.password = hashed_password
            db.session.commit()
            flash('Your password has been updated! You are now able to log in',category='success')
            return redirect(url_for('auth.login'))
    return render_template('reset_token.html',title='Reset Password',form=form,user=current_user)


