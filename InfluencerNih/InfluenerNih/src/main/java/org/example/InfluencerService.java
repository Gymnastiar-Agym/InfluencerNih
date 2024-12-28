package org.example;

import java.util.ArrayList;
import java.util.List;

public class InfluencerService {
    private List<Influencer> influencers;

    public InfluencerService() {
        influencers = new ArrayList<>();
    }

    // Create
    public void addInfluencer(Influencer influencer) {
        influencers.add(influencer);
    }

    // Read
    public List<Influencer> getInfluencers() {
        return influencers;
    }

    // Update
    public void updateInfluencer(int index, Influencer influencer) {
        if (index >= 0 && index < influencers.size()) {
            influencers.set(index, influencer);
        }
    }

    // Delete
    public void deleteInfluencer(int index) {
        if (index >= 0 && index < influencers.size()) {
            influencers.remove(index);
        }
    }

    // Search by name
    public List<Influencer> searchInfluencersByName(String name) {
        List<Influencer> result = new ArrayList<>();
        for (Influencer influencer : influencers) {
            if (influencer.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(influencer);
            }
        }
        return result;
    }
}
