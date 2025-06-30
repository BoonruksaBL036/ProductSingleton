/**
 * Interface for managing delete entities by ID.
 */
public interface Manageble {

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     * @return true if deletion was successful, false otherwise
     */
    boolean deleteById(int id);
}
