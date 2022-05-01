package me.jupdyke01.mtcore.settings;

import org.bukkit.ChatColor;

public class Settings {

    private boolean globalChat;
    private boolean staffChat;
    private boolean textDisplay;
    private ChatColor emoteColor;

    public Settings() {
        globalChat = true;
        staffChat = true;
        textDisplay = false;
        emoteColor = ChatColor.YELLOW;
    }

    public Settings(boolean globalChat, boolean staffChat, boolean textDisplay, ChatColor emoteColor) {
        this.globalChat = globalChat;
        this.staffChat = staffChat;
        this.textDisplay = textDisplay;
        this.emoteColor = emoteColor;
    }

    public boolean isGlobalChat() {
        return globalChat;
    }

    public void setGlobalChat(boolean globalChat) {
        this.globalChat = globalChat;
    }

    public boolean isStaffChat() {
        return staffChat;
    }

    public void setStaffChat(boolean staffChat) {
        this.staffChat = staffChat;
    }

    public boolean isTextDisplay() {
        return textDisplay;
    }

    public void setTextDisplay(boolean textDisplay) {
        this.textDisplay = textDisplay;
    }

    public ChatColor getEmoteColor() {
        return emoteColor;
    }

    public void setEmoteColor(ChatColor emoteColor) {
        this.emoteColor = emoteColor;
    }
}
