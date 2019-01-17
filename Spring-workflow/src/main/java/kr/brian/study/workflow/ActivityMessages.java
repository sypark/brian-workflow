package kr.brian.study.workflow;

import java.util.List;

import kr.brian.study.workflow.call.ActivityMessageDTO;

public interface ActivityMessages {

	List<ActivityMessageDTO> getActivityMessages();

    void setActivityMessages(List<ActivityMessageDTO> activityMessages);
    
}
