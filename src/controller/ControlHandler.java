package controller;

public interface ControlHandler {
	public abstract void handleTextInput(String input);
    public abstract void handleReset();
    public abstract void setLanguage(String language);
}
