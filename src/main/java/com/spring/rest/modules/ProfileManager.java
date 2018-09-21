package com.spring.rest.modules;

import com.spring.rest.models.ContentProfile;
import com.spring.rest.models.UserProfile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileManager{
    private static final Log LOG = LogFactory.getLog(ProfileManager.class);

    public UserProfile getUserProfile(String userId){
        return new UserProfile(userId, userId);
    }

    public ContentProfile getContentProfile(String contentId){
        return new ContentProfile(contentId, contentId);
    }
}
