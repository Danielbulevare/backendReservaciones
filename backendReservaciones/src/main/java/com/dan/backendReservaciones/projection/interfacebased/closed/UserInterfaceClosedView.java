package com.dan.backendReservaciones.projection.interfacebased.closed;

public interface UserInterfaceClosedView {
	Long getUserId();
	String getUserName();
	String getUserEmail();
	RoleInterfaceClosedView getUserRole();
}
