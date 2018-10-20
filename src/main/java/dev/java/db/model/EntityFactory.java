package dev.java.db.model;

public class EntityFactory {

    public static Entity getEntity(Table table) {
        switch (table) {
            case CANDIDATE:
                return new Candidate();
            case SKILL:
                return new Skill();
            case CANDIDATE_SKILL:
                return new CandidateSkill();
            case INTERVIEW:
                return new Interview();
            case USER:
                return new User();
            default:
                return null;
        }
    }
}