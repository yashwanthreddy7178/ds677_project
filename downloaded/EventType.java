package mobiletrain.org.myzhbj1.event_type;

import mobiletrain.org.myzhbj1.domain.NewsData;

/**
 * Created by Administrator on 2016/5/23.
 */
public class EventType {
    public static class ItemDataEvent{
        private NewsData data;
        public ItemDataEvent(NewsData data){
            this.data = data;
        }
        public NewsData getData(){
            return data;
        }
    }
}
