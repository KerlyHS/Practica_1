from flask import Flask
from routes.route import router

def create_app():
    app = Flask(__name__)
   
    app.config['SESSION_TYPE'] = 'null'

    with app.app_context():
        
        app.register_blueprint(router)
    return app