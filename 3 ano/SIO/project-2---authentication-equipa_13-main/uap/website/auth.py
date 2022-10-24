from os import error
from flask import Blueprint, render_template, request, flash
from flask.helpers import url_for
from flask_login.utils import login_user
from werkzeug.utils import HTMLBuilder, redirect
from website import uap_client
from flask_login import current_user
from flask import redirect,session


auth = Blueprint('auth',__name__)


@auth.route('/login', methods = ['GET', 'POST'])
def login():
        if request.method=='POST':
                username=request.form.get('username')
                password=request.form.get('password')
                passencry = request.form.get('passencry')
                if username==""or password=="" or passencry=="":
                        flash('Please enter username and password',category='error')
                        return render_template('login.html')
                user = uap_client.send(username,password,passencry)
                if user != False:
                        flash('Logged in sucessfully!',category='sucess')
                        return redirect(url_for('auth.profile',username=username,password=password))
                else:
                        flash('Invalid credentials, try again!', category='error')
                        return redirect(url_for('auth.login'))
        return render_template("login.html")


@auth.route ('/profile',methods=['GET','POST'])
def profile():
        username=request.args.get('username')
        session['username']=username

        return redirect('https://127.0.0.1:5000/profile')
