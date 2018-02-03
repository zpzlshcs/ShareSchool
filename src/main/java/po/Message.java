package po;

import java.util.Date;

public class Message {
    private Integer messageId;

    private Integer messageSendUserId;

    private Integer messageReceiveUserId;

    private String messageContent;

    private String messageImg;

    private Date messageCreatetime;

    private Integer messageType;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageSendUserId() {
        return messageSendUserId;
    }

    public void setMessageSendUserId(Integer messageSendUserId) {
        this.messageSendUserId = messageSendUserId;
    }

    public Integer getMessageReceiveUserId() {
        return messageReceiveUserId;
    }

    public void setMessageReceiveUserId(Integer messageReceiveUserId) {
        this.messageReceiveUserId = messageReceiveUserId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public String getMessageImg() {
        return messageImg;
    }

    public void setMessageImg(String messageImg) {
        this.messageImg = messageImg == null ? null : messageImg.trim();
    }

    public Date getMessageCreatetime() {
        return messageCreatetime;
    }

    public void setMessageCreatetime(Date messageCreatetime) {
        this.messageCreatetime = messageCreatetime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }
}