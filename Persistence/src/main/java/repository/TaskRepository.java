package repository;

import entities.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.HibernateUtils;
import validator.TaskValidator;

import java.util.Collection;
import java.util.Objects;

@Component
public class TaskRepository implements Repository<Integer, Task> {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public TaskRepository() {
        logger.traceEntry("Initializing Task Repository");
    }

    @Override
    public Task findOne(Integer id) {
        logger.traceEntry("Retrieving task by id.");

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Task game = session.createQuery("from Task where id = ?1", Task.class)
                        .setParameter(1, id)
                        .getSingleResult();

                transaction.commit();

                logger.traceExit("Successfully retrieved task by id!");

                return game;
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();

                logger.error("Error while retrieving task by id: " + ex);
            }
        }

        return null;
    }

    @Override
    public Collection<Task> findAll() {
        logger.traceEntry("Retrieving tasks.");

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Collection<Task> games = session.createQuery("from Task", Task.class).list();

                transaction.commit();

                logger.traceExit("Successfully retrieved tasks!");

                return games;
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();

                logger.error("Error while retrieving tasks: " + ex);
            }
        }

        return null;
    }

    @Override
    public void add(Task task) {
        logger.traceEntry("Adding task.");

        if (!TaskValidator.validate(task)) {
            logger.error("Invalid task data. Task not added.");
            return;
        }

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                session.persist(task);

                transaction.commit();

                logger.traceExit("Successfully added task!");
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();

                logger.error("Error while starting game: " + ex);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        logger.traceEntry("Deleting task.");

        Task task = findOne(id);

        if (!TaskValidator.validate(task)) {
            logger.error("Invalid task data. Task not deleted.");
            return;
        }

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                session.remove(task);

                transaction.commit();

                logger.traceExit("Successfully deleted task with id: " + task.getId());
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();

                logger.error("Error while deleting task: " + ex);
            }
        }
    }

    @Override
    public void update(Task task) {
        logger.traceEntry("Updating task.");

        if (!TaskValidator.validate(task)) {
            logger.error("Invalid task data. Task not updated.");
            return;
        }

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                if (!Objects.isNull(session.find(Task.class, task.getId()))) {
                    transaction = session.beginTransaction();

                    session.merge(task);
                    session.flush();

                    transaction.commit();

                    logger.traceExit("Successfully updated task!");
                }
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();

                logger.error("Error while updating task: " + ex);
            }
        }
    }
}
