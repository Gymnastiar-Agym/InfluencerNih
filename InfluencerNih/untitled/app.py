from flask import Flask
from influencers.routes import influencers_bp

app = Flask(__name__)

# Register the blueprint
app.register_blueprint(influencers_bp, url_prefix='/influencers')

if __name__ == '__main__':
    app.run(debug=True)