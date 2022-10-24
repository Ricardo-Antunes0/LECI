from website import create_app
from http.server import HTTPServer, SimpleHTTPRequestHandler
import ssl


app = create_app()
if __name__ == '__main__':
        app.run(ssl_context=('cert.pem', 'key.pem'))
    
