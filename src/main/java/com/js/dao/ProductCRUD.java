package com.js.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.js.dto.Product;

public class ProductCRUD {
	public static final String URL="jdbc:mysql://localhost:3306/product_store";
	public static final String USER="root";
	public static final String PWD="vinushree";
	public static final String PATH="com.mysql.cj.jdbc.Driver";
	
   public static int insertProduct(Product p) {
	   Connection c=null;
	   String query="insert into Product values(?,?,?,?,?)";
	   try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		ps.setInt(1,p.getId());
		ps.setString(2,p.getName());
		ps.setString(3,p.getBrand());
		ps.setDouble(4,p.getPrice());
		ps.setInt(5,p.getId());
		return ps.executeUpdate();
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	   finally {
		   try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   }
   return 0;
   }
   
   public int deleteProductById(int id) {
		String query="delete from product where id=?";
		Connection c=null;
		try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		ps.setInt(1, id);
		return ps.executeUpdate();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}


	finally {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return 0;
	}
		
	public int updateProduct(int id, Product p) {
		
		String query="delete from product where id=?";
		Connection c=null;
		try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		ps.setString(1,p.getName());
		ps.setString(1,p.getBrand());
		ps.setDouble(1,p.getPrice());
		ps.setInt(1,p.getQuantity());
		return ps.executeUpdate();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}


	finally {
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
	}
 


public int deleteProductByPrice(double price) {
	String query="delete from product where id=?";
	Connection c=null;
	try {
	Class.forName(PATH);
	c=DriverManager.getConnection(URL,USER,PWD);
	PreparedStatement ps=c.prepareStatement(query);
	ps.setDouble(1,price);
	return ps.executeUpdate();
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}catch (SQLException e) {
		e.printStackTrace();
	}


finally {
	try {
		c.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
return 0;
}

public Product getProductById(int id) {
	String query="select* from product where id=?";
		Connection c=null;
	try {
     Class.forName(PATH);
     c=DriverManager.getConnection(URL, USER, PWD);
     PreparedStatement ps=c.prepareStatement(query);
     ps.setInt(1,id);
     ResultSet rs=ps.executeQuery();
     if(rs.next()) {
    	 Product p=new Product();
    	 p.setId(rs.getInt(1));
    	 p.setName(rs.getString(2));
    	 p.setBrand(rs.getString(3));
    	 p.setPrice(rs.getDouble(4));
    	 p.setQuantity(rs.getInt(5));
    	 return p;
     }
}  catch (ClassNotFoundException e) {
	e.printStackTrace();
} catch (SQLException e) {
	e.printStackTrace();
}
finally {
	try {
		c.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
return null;
}


public ArrayList<Product> getAllProduct(){
	ArrayList<Product> al=new ArrayList<Product>();
	String query="select*from product";
	Connection c=null;
	try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		 PreparedStatement ps=c.prepareStatement(query);
         ResultSet rs=ps.executeQuery();
	     while(rs.next()) {
	    	 Product p=new Product();
	    	 p.setId(rs.getInt(1));
	    	 p.setName(rs.getString(2));
	    	 p.setBrand(rs.getString(3));
	    	 p.setPrice(rs.getDouble(4));
	    	 p.setQuantity(rs.getInt(5));
	    	 al.add(p);//adding every product into array list
	} 
	  return al;   
	}  catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}                                                      

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome to Product Store");
    boolean b = true;
    while (b) {
        System.out.println("1.Insert Product");
        System.out.println("2.Delete Product");
        System.out.println("3.Update product");
        System.out.println("4.Get Product By Id");
        System.out.println("5.Get All Products");
        System.out.println("6.Exit");
        int a = sc.nextInt();

        switch (a) {
        case 1:{
            Product pro = new Product();
            System.out.println("Enter the Product Id");
            pro.setId(sc.nextInt());
            System.out.println("Enter the Product Name");
            pro.setName(sc.next());
            System.out.println("Enter the Product Brand");
            pro.setBrand(sc.next());
            System.out.println("Enter the Product Price");
            pro.setPrice(sc.nextDouble());
            System.out.println("Enter the Product Quantity");
            pro.setQuantity(sc.nextInt());
           
            int res = ProductCRUD.insertProduct(pro);
            if(res==1) {
                System.out.println("Inserted Successfully");
            }else {
                System.out.println("Falied To Insert");
            }
        }break;
        case 2:{
            System.out.println("Enter the Product id to delete");
            int id = sc.nextInt();
            ProductCRUD pc = new ProductCRUD();
            int res = pc.deleteProductById(id);
            if(res==1) {
                System.out.println("Deleted");
            }else {
                System.out.println("No Product with Given Id Found");
            }
        }break;
        case 3:{
            System.out.println("Enter the Product id to Update");
            int id = sc.nextInt();
           
            Product pro = new Product();
            System.out.println("Enter the Product Name");
            pro.setName(sc.next());
            System.out.println("Enter the Product Brand");
            pro.setBrand(sc.next());
            System.out.println("Enter the Product Price");
            pro.setPrice(sc.nextDouble());
            System.out.println("Enter the Product Quantity");
            pro.setQuantity(sc.nextInt());
           
            ProductCRUD pc = new ProductCRUD();
            int res = pc.updateProduct(id, pro);
            if(res>0) {
                System.out.println("Updated");
            }else {
                System.out.println("No Product with Given Id Found");
            }
        }break;
        case 4:{
            System.out.println("Enter the product Id");
            int id = sc.nextInt();
            ProductCRUD pc = new ProductCRUD();
            Product p = pc.getProductById(id);
            if(p!=null) {
                System.out.println(p);
            }else {
                System.out.println("No Product Found");
            }
        }break;
        case 5:{
            ProductCRUD pc = new ProductCRUD();
            ArrayList<Product> products = pc.getAllProduct();
            if(products.size()>0) {
                for(Product  p : products) {
                    System.out.println(p);
                }
            }else {
                System.out.println("No Products Available");
            }
        }break;
        case 6: {
            b = false;
            System.out.println("Thank You");

        }
        }
    }
}

public int[] batchExecutionToInsert(ArrayList<Product> products) {
	Connection c=null;
	String query="insert into product values(?,?,?,?,?)";
	try {
	Class.forName(PATH);
	c=DriverManager.getConnection(URL,USER,PWD);
	PreparedStatement ps=c.prepareStatement(query);
	for(Product p:products) {
		ps.setInt(1,p.getId());
		ps.setString(2,p.getName());
		ps.setString(3,p.getBrand());
		ps.setDouble(4,p.getPrice());
	    ps.setInt(5,p.getQuantity());
		ps.addBatch();
	}
      return ps.executeBatch();
      
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return null;

}
public int[] batchExecutionToDelete(int[] arr) {
	Connection c=null;
	String query="insert into product where id=";
	try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		for(int x:arr) {
			ps.setInt(1, x);
			ps.addBatch();
		}
return ps.executeBatch();
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}catch (SQLException e) {
	e.printStackTrace();
}
finally {
	try {
		c.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	return null;
}
}