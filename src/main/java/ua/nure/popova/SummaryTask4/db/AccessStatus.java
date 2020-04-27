package ua.nure.popova.SummaryTask4.db;

import ua.nure.popova.SummaryTask4.db.entity.Enrollee;

/**
 * The enum Access status.
 *
 * @author A.Popova
 */
public enum AccessStatus {

    LOCKED, UNLOCKED;

    // 0 - unlocked
    // 1 - locked

    /**
     * Gets access status.
     *
     * @param somebody the somebody
     * @return the access status
     */
    public static AccessStatus getAccessStatus(Enrollee somebody) {
        boolean accessStatus = somebody.isAccessAllowed();
        int access = 0;

        if(accessStatus){
            access=1;
        }
        System.out.println(access);
        return AccessStatus.values()[access];
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
