public class App {
    public static void main(String[] args) throws Exception {
        //Create database instance
        Database db=new Database();
        //Create table
        db.createTable("users");
        Table users= db.getTable("users");
        users.addColumn("id", DataType.INT, 0, true);
        users.addColumn("name", DataType.VARCHAR, 20, false);
        users.addColumn("Age", DataType.INT, 3, false);

        //Insert a row into users table
        Row row1=new Row();
        row1.setColumnValue(users.getColumnByName("id"), 1);
        row1.setColumnValue(users.getColumnByName("name"), "saurav");
        row1.setColumnValue(users.getColumnByName("Age"), 23);
        db.insertIntoTable("users", row1);

        Row row2=new Row();
        row2.setColumnValue(users.getColumnByName("id"),2);
        row2.setColumnValue(users.getColumnByName("name"), "nitish");
        row2.setColumnValue(users.getColumnByName("Age"), 25);
        db.insertIntoTable("users", row2);

        //Select all rows
        System.out.println("All rows");
        db.selectAllFromTable("users");

        // Select a row by primary key (uses the index)
        System.out.println("\nSelect by primary key (1):");
        db.selectByPrimaryKey("users", 1);

        // Update a row (index updated)
        db.updateTable("users", 1, "name", "Alice Updated");
        System.out.println("\nAfter update:");
        db.selectAllFromTable("users");

        // Delete a row (index updated)
        db.deleteFromTable("users", 2);
        System.out.println("\nAfter deletion:");
        db.selectAllFromTable("users");

    }
}
