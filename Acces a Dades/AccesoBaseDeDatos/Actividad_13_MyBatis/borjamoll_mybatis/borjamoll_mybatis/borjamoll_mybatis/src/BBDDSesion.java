import org.apache.ibatis.session.SqlSession;

/**
 * Gestion de la sesion
 * 
 */
public class BBDDSesion
{
    // Sesion para acceso a datos de la bbdd
    private SqlSession session;

    // Flag que indica si se requiere commit
    private boolean requiereCommit=false;

    /**
     * Constructor
     */
    public BBDDSesion(){}

    /**
     * Establecer que se requiere commit
     */
    public void setRequiereCommit(){requiereCommit=true;}

    /**
     * Obtener la sesion de la base de datos
     * 
     * @return Sesion con la base de datos
     */
    public SqlSession getSession(){return session;}

    /**
     * Ejecutamos commit si se requiere en alg�n momento puntual.
     */
    public void executeCommit()
    {
        if (session!=null)if(requiereCommit)session.commit();
    }

    /**
     * Inicializar una nueva sesion con la base de datos
     */
    public void initSession()
    {
        // Iniciamos una sesion
        if (session==null)
        {
            System.out.println("BBDD  ---------------------------- SESION ABIERTA -------------------------");
            session=BBDDManager.sqlMapper.openSession();
        }
    }

    /**
     * Cerrar la sesion cuando este abierta
     */
    public void closeSession()
    {
        if (session!=null)
        {
            if(requiereCommit)session.commit();
            session.close();
            System.out.println("BBDD : ---------------------------- SESION CERRADA -------------------------");
        }
    }
}