package com.hexa.innovation.RestWS;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONObject;

@Path("/employees")
public class JSONService {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployeeInJSON(@PathParam("id") int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Employee employee = (Employee) session.get(Employee.class, id);
		session.getTransaction().commit();
		session.close();

		return employee;
	}

	/*
	 * @POST
	 * 
	 * @Path("/insertEmployee")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public void consumeJSON(Employee
	 * emp, @Context HttpServletRequest request) {
	 * 
	 * //System.out.println("**************    In consumeJSON   **************");
	 * System.out.println("***************   empid **************"+
	 * emp.getEmpId()); Session session =
	 * HibernateUtil.getSessionFactory().openSession();
	 * session.beginTransaction(); Employee employee = new
	 * Employee(emp.getEmpId(), emp.getEmpName()); session.save(employee);
	 * session.getTransaction().commit(); session.close();
	 * 
	 * //return Response.status(200).entity(output).build(); }
	 */

	@POST
	@Path("/insertEmployee/{empId}/{empName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consumeJSON(@PathParam("empId") String id,
			@PathParam("empName") String name) {

		System.out.println("**************    In consumeJSON   **************");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Employee employee = new Employee();
		employee.setEmpId(Integer.parseInt(id));
		employee.setEmpName(name);
		String result = null;
		try {
			session.save(employee);
			session.getTransaction().commit();
			result = "inserted successfully";
		} catch (Exception e) {
			result = "Insertion failed";
		}
		session.close();
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/insertEmp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertJSON(String json) {
		
		String result = null;
		System.out.println("**************    In consumeJSON   **************"+json);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
      
		try {
			JSONObject jsonObject = new JSONObject(json);
			
			String strid = (String)jsonObject.get("id");
			String name = (String)jsonObject.get("name");
			
			System.out.print("Id "+strid +"\n Name :"+name);

			Employee employee = new Employee();
			employee.setEmpId(Integer.valueOf(strid));
			employee.setEmpName(name);
			
			session.save(employee);
			session.getTransaction().commit();
			result = "inserted successfully";
			
		}/* catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("error json"+e);
		}*/
		  catch(Exception e){
	        	result = "Insertion failed";
	        	System.out.println("error "+e);
	    }

		session.close();
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/emplist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployeesInJSON() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query queryResult = session.createQuery("from Employee");
		List<Employee> list = queryResult.list();
		System.out.println("employee : " + list.size());
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@DELETE
	@Path("/deleteEmployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteById(@PathParam("id") int id) {
		System.out.println("========inside delete  =========");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Object persistentInstance = session.load(Employee.class, id);
		if (persistentInstance != null) {
			session.delete(persistentInstance);
			session.getTransaction().commit();
			return true;
		}
		session.getTransaction().commit();
		session.close();
		return false;
	}
}