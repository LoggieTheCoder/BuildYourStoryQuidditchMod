package fr.loggie.bys.utils;

import net.minecraft.util.text.TextComponentTranslation;

public enum Messages {
    CAUGHT_GOLDEN_SNITCH("messages.golden_snitch_caught"),
    LIMITS_NOT_SET("messages.limits_not_set"),
    NOT_IN_ZONE("messages.not_in_zone"),
    FIRST_POS_SET("messages.first_pos_set"),
    SECOND_POS_SET("messages.second_pos_set"),
    SORT_SET("messages.sort_set"),
    SORT_APPLIED("messages.sort_applied")
    ;
    String message;
    Messages(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public TextComponentTranslation getTranslationMessage(){
        return new TextComponentTranslation(this.message);
    }
    public String getTranslatedText(){
        return this.getTranslationMessage().getFormattedText();
    }
}
