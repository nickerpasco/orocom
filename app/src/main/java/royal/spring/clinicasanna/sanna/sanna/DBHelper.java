package royal.spring.clinicasanna.sanna.sanna;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import royal.spring.clinicasanna.sanna.omorocom.ui.AsistenciaDiariaMarcas;
import royal.spring.clinicasanna.sanna.sanna.clases.FuncionesVitales;
import royal.spring.clinicasanna.sanna.sanna.clases.Paciente;
import royal.spring.clinicasanna.sanna.sanna.clases.Usuario;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final String DB_NAME = "Sanna.db";
    private static final int DB_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
        try {

            // Create Table with given table name with columnName
            TableUtils.createTable(cs, Usuario.class);
            TableUtils.createTableIfNotExists(cs, FuncionesVitales.class);
            TableUtils.createTableIfNotExists(cs, Paciente.class);
            TableUtils.createTableIfNotExists(cs, AsistenciaDiariaMarcas.class);
            //TableUtils.createTableIfNotExists(cs, FuncionesVitales.class);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {

    }

    public <T> void deleteAsitencia(Class<T> clazz, int IdAsistencia) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);


            DeleteBuilder<AsistenciaDiariaMarcas, Integer> deleteBuilder = (DeleteBuilder<AsistenciaDiariaMarcas, Integer>) dao.deleteBuilder();
            deleteBuilder.where().eq("IdAsistencia", IdAsistencia);
            deleteBuilder.delete();



    }

    public <T> long getCountTable(Class<T> clazz,String nameClass) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        GenericRawResults<String[]> rawResults = dao.queryRaw("select count(*) from "+nameClass);
        List<String[]> results = rawResults.getResults();
        String[] resultArray = results.get(0);

        return Long.parseLong(resultArray[0]);

    }

    public <T> List<T> getAll(Class<T> clazz) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryForAll();
    }

    public <T> List<T> getAllQueryByIDPEDIDO(Class<T> clazz,String Id) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);



        return (List<T>) dao.queryBuilder().where().eq("PedidoId",Id);
    }

    public <T> List<T> getAllOrdered(Class<T> clazz, String orderBy, boolean ascending) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryBuilder().orderBy(orderBy, ascending).query();
    }

    public <T> void fillObject(Class<T> clazz, T aObj) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        dao.createOrUpdate(aObj);
    }

    public <T> void fillObjects(Class<T> clazz, Collection<T> aObjList) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        for (T obj : aObjList) {
            dao.createOrUpdate(obj);
        }
    }

    public <T> T getById(Class<T> clazz, Object aId) throws SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.queryForId(aId);
    }

    public <T> List<T> query(Class<T> clazz, Map<String, Object> aMap) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);

        return dao.queryForFieldValues(aMap);
    }





    public <T> List<T> queryNot(Class<T> clazz, String columnName, String value) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);

        return dao.queryBuilder().where().ne(columnName, value).query();
    }

    public <T> T queryFirst(Class<T> clazz, Map<String, Object> aMap) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        List<T> list = dao.queryForFieldValues(aMap);
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }
    public <T> Dao.CreateOrUpdateStatus createOrUpdate(T obj) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.createOrUpdate(obj);
    }

    public <T> int create(T obj) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.create(obj);
    }



    public  int DeleteQuery(Object ob, String query) throws SQLException {
        Dao  dao = getDao(ob.getClass());
        return dao.executeRaw(query);
    }

    public  int DeleteQuery2(Object ob, String query) throws SQLException {
        Dao  dao = getDao(ob.getClass());
        GenericRawResults<String[]> rawResults = dao.queryRaw("SELECT * FROM Productos");


        return dao.executeRaw(query);
    }

    /*

    public int updateRecordToNewUser( PedidosDataItem ob) throws SQLException {

        Dao  dao = getDao(ob.getClass());

        UpdateBuilder<PedidosDataItem, ?> ub = dao.updateBuilder();
        try
        {
            ub.updateColumnValue("Estado", ob.getEstado());
            ub.where().isNull("PedidoId").or().eq("PedidoId", ob.getPedidoId());

            return ub.update();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return -1;
    }

     */


    public <T> int update(T obj) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.update(obj);
    }




    public <T> int deleteById(Class<T> clazz, Object aId) throws SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.deleteById(aId);
    }

    public <T> int deleteObjects(Class<T> clazz, Collection<T> aObjList) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);

        return dao.delete(aObjList);
    }

    public <T> void deleteAll(Class<T> clazz) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        dao.deleteBuilder().delete();
    }

    /*
    public <T> void deleteAllQueryWhere(Class<T> clazz,int idPedido,int Linea,String tipo) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);

        if(tipo.equals("Cabecera")){
            DeleteBuilder<PedidosDataItem, Integer> deleteBuilder = (DeleteBuilder<PedidosDataItem, Integer>) dao.deleteBuilder();
            deleteBuilder.where().eq("PedidoId", idPedido);
            deleteBuilder.delete();
        }else if(tipo.equals("Detalle")){
            DeleteBuilder<PedidoDetalle, Integer> deleteBuilder = (DeleteBuilder<PedidoDetalle, Integer>) dao.deleteBuilder();
            deleteBuilder.where().eq("PedidoId", idPedido);
            deleteBuilder.where().eq("Linea", Linea);
            deleteBuilder.delete();
        }else if(tipo.equals("DetallePorIp")){
            DeleteBuilder<PedidoDetalle, Integer> deleteBuilder = (DeleteBuilder<PedidoDetalle, Integer>) dao.deleteBuilder();
            deleteBuilder.where().eq("PedidoId", idPedido);
            deleteBuilder.delete();
        }



    }

     */

    public static HashMap<String, Object> where(String aVar, Object aValue) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put(aVar, aValue);
        return result;
    }
}
