package com.dan.backendReservaciones.projection.interfacebased.closed;

public interface BoardInterfaceClosedView {
	Long getBoardId();
	String getBoardNumber();
	boolean getIsReserved();
	String getBlockedByUser();
}
