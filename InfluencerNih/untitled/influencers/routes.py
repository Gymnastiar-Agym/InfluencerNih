from flask import Blueprint, request, jsonify

influencers_bp = Blueprint('influencers', __name__)

# In-memory database (for simplicity)
influencers = []

@influencers_bp.route('/', methods=['GET'])
def get_influencers():
    return jsonify(influencers)

@influencers_bp.route('/', methods=['POST'])
def add_influencer():
    influencer = request.get_json()
    influencers.append(influencer)
    return jsonify(influencer), 201

@influencers_bp.route('/<int:id>', methods=['PUT'])
def update_influencer(id):
    influencer = request.get_json()
    influencers[id] = influencer
    return jsonify(influencer)

@influencers_bp.route('/<int:id>', methods=['DELETE'])
def delete_influencer(id):
    influencers.pop(id)
    return '', 204