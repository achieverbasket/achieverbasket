package com.ab.type;

public enum NotificationType {
	
	EMAIL_NOTIFICATION(1),
	MESSAGE_NOTIFICATION(2),
	WHATSAPP_NOTIFICATION(3),
	OTHER(9);
	
	private int notificationTypeId;
	private NotificationType(int notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}
	
	public static NotificationType fromId(int notificationTypeId) {
		for (NotificationType notificationType : NotificationType.values()) {
			if(notificationType.notificationTypeId == notificationTypeId) {
				return notificationType;
			}
		}
		return NotificationType.OTHER;
	}

	public int getNotificationTypeId() {
		return notificationTypeId;
	}

}
