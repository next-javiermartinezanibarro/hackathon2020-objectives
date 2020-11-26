package com.bbva.hackathon.bbvakids.objective;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;


@Schema(description = "The objective")
@Entity
public class Objective extends PanacheEntity {
    public String name;
    public long profileId;
    public double requiredMoney;
}
