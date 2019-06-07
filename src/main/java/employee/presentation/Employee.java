package employee.presentation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "findAllEmployees", query = "SELECT e FROM Employee e")
public class Employee {
	   /**
	    * Primary key of this entity 
	    */
	   @Id
	   @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_EMPLOYEE")
	   @SequenceGenerator(name="SEQ_EMPLOYEE", sequenceName="seq_employee", allocationSize =1)
	   private Long id;

	   /**
	    * Name of the employee
	    */
	   private String name;

	   @Temporal(TemporalType.DATE)      //signifies a date attribute
	   private Date dob;
	   //-------------------------------------------------------------------------------------||
	   // Constructor ------------------------------------------------------------------------||
	   //-------------------------------------------------------------------------------------||

	   /**
	    * Default constructor, required by JPA
	    */
	   public Employee()
	   {

	   }

	   /**
	    * Convenience constructor
	    */
	   Employee(final long id, final String name)
	   {
	      // Set
	      this.id = id;
	      this.name = name;
	   }

	   //-------------------------------------------------------------------------------------||
	   // Accessors / Mutators ---------------------------------------------------------------||
	   //-------------------------------------------------------------------------------------||

	   /**
	    * @return the id
	    */
	   public Long getId()
	   {
	      return id;
	   }

	   /**
	    * @param id the id to set
	    */
	   public void setId(final Long id)
	   {
	      this.id = id;
	   }

	   /**
	    * @return the name
	    */
	   public String getName()
	   {
	      return name;
	   }

	   /**
	    * @param name the name to set
	    */
	   public void setName(final String name)
	   {
	      this.name = name;
	   }

	   
	   public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	    * {@inheritDoc}
	    * @see java.lang.Object#toString()
	    */
	   @Override
	   public String toString()
	   {
	      return Employee.class.getSimpleName() + " [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	   }

	   /**
	    * {@inheritDoc}
	    * @see java.lang.Object#hashCode()
	    */
	   @Override
	   public int hashCode()
	   {
	      final int prime = 31;
	      int result = 1;
	      result = prime * result + ((id == null) ? 0 : id.hashCode());
	      return result;
	   }

	   /**
	    * {@inheritDoc}
	    * @see java.lang.Object#equals(java.lang.Object)
	    */
	   @Override
	   public boolean equals(Object obj)
	   {
	      if (this == obj)
	         return true;
	      if (obj == null)
	         return false;
	      if (getClass() != obj.getClass())
	         return false;
	      Employee other = (Employee) obj;
	      if (id == null)
	      {
	         if (other.id != null)
	            return false;
	      }
	      else if (!id.equals(other.id))
	         return false;
	      return true;
	   }

}
