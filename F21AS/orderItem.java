package F21AS;
/**
 * Holds all the data for one order
 */
public class orderItem {

	private  Integer id;
    /**
     * 
     */
    private Integer tableId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer quantity;

    private orderStatus status;

    /**
     * 
     * @param id
     * @param TableId
     * @param Name
     * @param Quantity
     */
    public orderItem(Integer Id, Integer TableId, String Name, Integer Quantity) {
    	this(Id, TableId, Name, Quantity, orderStatus.Delivered);
    }
    
    public orderItem(Integer Id, Integer TableId, String Name, Integer Quantity, orderStatus status) {
    	this.id = Id;
    	this.tableId=TableId;
    	this.name=Name;
    	this.quantity=Quantity;
    	this.status=status;
    }


    /**
     * @param TableId 
     * @param Name 
     * @param Quantity
     */
    public orderItem(Integer TableId, String Name, Integer Quantity) {
    	this(new Log().getNextID(), TableId, Name, Quantity);
    }

    /**
     * @param TableId 
     * @return
     */
    public void setTable(Integer TableId) {
    	this.tableId=TableId;
    }

    /**
     * @param Name 
     * @return
     */
    public void setName(String Name) {
    	this.name=Name;
    }


    /**
     * @param Quantity 
     * @return
     */
    public void setQuantity(Integer Quantity) {
    	this.quantity=Quantity;
    }

    /**
     * @return
     */
    public Integer getTableId() {
        // TODO implement here
        return tableId;
    }

    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return name;
    }

    
    /**
     * @return
     */
    public Integer getQuantity() {
        // TODO implement here
        return quantity;
    }

	/**
	 * @return the status
	 */
	public orderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(orderStatus status) {
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

    public String toString() {
    	return this.id + ": " + this.getName() + " * " + this.getQuantity() + " on " + this.getTableId();
    }
}