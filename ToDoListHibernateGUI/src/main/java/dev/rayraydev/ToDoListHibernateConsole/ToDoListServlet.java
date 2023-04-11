package dev.rayraydev.ToDoListHibernateConsole;

import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ToDoListServlet", value = "/toDoList")
public class ToDoListServlet extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() {

        entityManagerFactory = Persistence.createEntityManagerFactory("ToDoListApp");
    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonClicked = request.getParameter("button");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            switch (buttonClicked) {
                case "view" -> {
                    TypedQuery<ToDoList> query = entityManager.createQuery("SELECT t FROM ToDoList t", ToDoList.class);
                    List<ToDoList> toDoList = query.getResultList();
                    request.setAttribute("toDoList", toDoList);
                    request.getRequestDispatcher("/view.jsp").forward(request, response);
                }
                case "add" -> {
                    String note = request.getParameter("note");
                    ToDoList item = new ToDoList();
                    item.setNote(note);
                    entityManager.persist(item);
                    transaction.commit();
                    response.sendRedirect("toDoList");
                }
                case "deleteSelected" -> {
                    String[] deleteIds = request.getParameterValues("deleteIds");
                    if (deleteIds != null && deleteIds.length > 0) {
                        for (int i = 0; i < deleteIds.length; i++) {
                            if (deleteIds[i] != null && !deleteIds[i].isEmpty()) {
                                int id = Integer.parseInt(deleteIds[i]);
                                ToDoList item = entityManager.find(ToDoList.class, id);
                                if (item != null) {
                                    entityManager.remove(item);
                                }
                            }
                        }
                        transaction.commit();
                    }
                    response.sendRedirect("toDoList");
                }
                default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            entityManager.close();
        }
    }
}