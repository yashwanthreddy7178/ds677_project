package jwbexam.repository;

import jwbexam.domain.entities.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {
    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Document save(Document entity) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();

            return entity;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public Document update(Document entity) {
        this.entityManager.getTransaction().begin();
        try {
            Document updatedDoc = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedDoc;
        }catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<Document> findAll() {
        return this.entityManager
                .createQuery("SELECT d FROM documents d", Document.class)
                .getResultList();
    }

    @Override
    public Document findById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT d FROM documents d WHERE d.id = :id", Document.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("SELECT count(d) FROM documents d", Long.class)
                .getSingleResult();
    }

    @Override
    public void print(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("DELETE FROM documents d WHERE d.id = :id")
                .setParameter("id", id).executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
