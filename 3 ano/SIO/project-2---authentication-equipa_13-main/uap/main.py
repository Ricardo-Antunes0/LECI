from website import create_app
from http.server import HTTPServer, SimpleHTTPRequestHandler
import ssl


app = create_app()
if __name__ == '__main__':
        app.run(host='127.0.0.1', port=5002)
