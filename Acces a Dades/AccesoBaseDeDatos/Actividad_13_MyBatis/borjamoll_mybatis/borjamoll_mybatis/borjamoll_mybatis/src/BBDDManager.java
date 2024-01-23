import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Manager para controlar la conexion con la base de datos
 * 
 */
public class BBDDManager
{
    // Conexion con la base de datos 
    public static SqlSessionFactory sqlMapper;

    // Cargamos la conexion con la base de datos
    static
    {
         try
         {
             Reader reader=Resources.getResourceAsReader("mybatis.xml");
             sqlMapper=new SqlSessionFactoryBuilder().build(reader);
             System.out.println("BBDDManager: SQLMapper iniciado correctamente:");
         }
         catch (Exception e)
         {
             throw new RuntimeException("No se ha podido acceder a la bbdd"+e,e);
         }
    }
}