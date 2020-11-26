package com.bbva.hackathon.bbvakids.objective;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class ObjectiveService {
    @Transactional(SUPPORTS)
    public List<Objective> findAllObjectivesByProfileId(Long profileId) {
        return Objective.find("profileId", profileId).list();
    }

    @Transactional(SUPPORTS)
    public Objective findObjectiveById(Long id) {
        return Objective.findById(id);
    }

    public Objective persistObjective(@Valid Objective objective) {
        Objective.persist(objective);
        return objective;
    }

    public void deleteObjective(Long id) {
        Objective objective = Objective.findById(id);
        objective.delete();
    }
}
