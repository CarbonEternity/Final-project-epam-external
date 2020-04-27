package ua.nure.popova.SummaryTask4.db;

import ua.nure.popova.SummaryTask4.db.entity.Enrollee;

/**
 * The enum Entrance status.
 *
 * @author A.Popova
 */
public enum EntranceStatus {

    DIDNT_ENTER, ENTERED;

    // 0 - didn't enter
    // 1 - entered

    /**
     * Gets entrance status.
     *
     * @param somebody enrollee
     * @return the entrance status
     */
    public static EntranceStatus getEntranceStatus(Enrollee somebody) {
        int entranceStatus = somebody.getEntranceStatus();
        System.out.println(entranceStatus);
        return EntranceStatus.values()[entranceStatus];
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
