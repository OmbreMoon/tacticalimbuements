package net.m3tte.tactical_imbuements.definitions;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.SimpleParticleType;

public class ImbuementDef {
    final String identifier;
    final SimpleParticleType emitParticle;
    final float rCol;
    final float bCol;
    final float gCol;

    String fireOverlayIdent;

    public ImbuementDef(String identifier, SimpleParticleType emitParticle, float rCol, float gCol, float bCol, String fireOverlayIdent) {
        this.identifier = identifier;
        this.emitParticle = emitParticle;
        this.rCol = rCol;
        this.bCol = bCol;
        this.gCol = gCol;
        this.fireOverlayIdent = fireOverlayIdent;
    }

    public String getIdentifier() {
        return identifier;
    }


    public SimpleParticleType getEmitParticle() {
        return emitParticle;
    }



    public float getrCol() {
        return rCol;
    }


    public float getbCol() {
        return bCol;
    }



    public float getgCol() {
        return gCol;
    }



    public String getFireOverlayIdent() {
        return fireOverlayIdent;
    }


}
