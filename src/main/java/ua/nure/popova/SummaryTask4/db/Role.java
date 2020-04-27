package ua.nure.popova.SummaryTask4.db;

import ua.nure.popova.SummaryTask4.db.entity.Entity;

/**
 * The enum Role.
 *
 * @author A.Popova
 */
public enum Role {

    ADMIN, CLIENT;

    // 0 - admin
    // 1 - enrollee

    /**
     * Gets role.
     *
     * @param somebody enrollee or admin
     * @return the role
     */
    public static Role getRole(Entity somebody) {
        int roleId = somebody.getRoleId();
        System.out.println(roleId);
        return Role.values()[roleId];
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name().toLowerCase();
    }

}
