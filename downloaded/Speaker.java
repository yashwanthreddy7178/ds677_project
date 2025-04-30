package uk.ac.aber.dcs.cs31620.conferenceapp.model;

import java.io.Serializable;

public class Speaker implements Serializable {
    private String m_speakerName;
    private String m_speakerId;
    private String m_twitterHandle;
    private String m_speakerDetails;

    public Speaker(String speakerName, String speakerId, String twitterHandle,
                   String speakerDetails) {
        m_speakerName = speakerName;
        m_speakerId = speakerId;
        m_twitterHandle = twitterHandle;
        m_speakerDetails = speakerDetails;
    }

    public String getSpeakerName() {
        return m_speakerName;
    }

    public String getSpeakerId() {
        return m_speakerId;
    }

    public String getTwitterHandle() {
        return m_twitterHandle;
    }

    public String getSpeakerDetails() {
        return m_speakerDetails;
    }
}
