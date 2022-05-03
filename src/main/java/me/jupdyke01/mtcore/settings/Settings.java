package me.jupdyke01.mtcore.settings;

import org.bukkit.ChatColor;

public class Settings {

    private boolean globalChat;
    private boolean staffChat;
    private boolean textDisplay;
    private boolean defaultGlobal;
    private ChatColor emoteColor;

    public Settings() {
        globalChat = true;
        staffChat = true;
        textDisplay = false;
        defaultGlobal = false;
        emoteColor = ChatColor.YELLOW;
    }

    public Settings(boolean globalChat, boolean staffChat, boolean textDisplay, boolean defaultGlobal, ChatColor emoteColor) {
        this.globalChat = globalChat;
        this.staffChat = staffChat;
        this.textDisplay = textDisplay;
        this.defaultGlobal = defaultGlobal;
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

    public boolean isDefaultGlobal() {
        return defaultGlobal;
    }

    public void setDefaultGlobal(boolean defaultGlobal) {
        this.defaultGlobal = defaultGlobal;
    }
}
