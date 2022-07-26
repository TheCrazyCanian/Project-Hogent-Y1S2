module Rummikub_2021_start {
	exports persistentie;
	exports cui;
	exports utility;
	exports gui;
	exports main;
	exports domein;
	exports exceptions;

	requires java.desktop;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	
	opens gui;
}