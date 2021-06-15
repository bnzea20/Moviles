package com.interfaces.pokedex.pokeAPI.modelos;

import com.google.gson.annotations.SerializedName;

public class Abilities {
    private Ability ability;
    @SerializedName("is_hidden")
    private boolean isHidden;
    private int slot;

    public Ability getAbility() {
        return ability;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public int getSlot() {
        return slot;
    }
}
