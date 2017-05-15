package com.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xutils.DbManager;
import org.xutils.x;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

import com.app.model.ConfigModel;
import com.app.model.NoteModel;
import com.app.utils.ToastUtils;

public class DaoMs {
	
	public static DaoMs daoMs;
	
	public DaoMs(){}
	
	public static DaoMs getInstance(){
		if(daoMs==null){
			daoMs =  new DaoMs();
		}
		
		return daoMs;
	}
	
	protected DbManager db;
	public void initDb(){
	        //Local data initialization
	        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
	                .setDbName("text_reader_db") //Set the database name
	                .setDbVersion(1) //Set the version of the database, which will be checked each time the application is started,
	                //Found that the database version is lower than the value set here will be a database upgrade and trigger DbUpgradeListener
	                .setAllowTransaction(true)//Set whether to open the transaction, the default is false to close the transaction
	                .setTableCreateListener(new DbManager.TableCreateListener() {
	                   
						@Override
						public void onTableCreate(DbManager arg0,
								TableEntity<?> arg1) {
							// TODO Auto-generated method stub
							
						}
	                })//Set the Listener when the database is created
	                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
	                    @Override
	                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
	                        //balabala...
	                    }
	                });//Set the database upgrade Listener, where you can perform the relevant changes to the database table, such as alter statement to increase the field, etc.
	                //.setDbDir(null);//Set the database .db file stored in the directory, the default package name under the catalog directory
	        db = x.getDb(daoConfig);
	}
	
	
	public void dbAdd() throws DbException {
        //User user = new User("Kevingo","caolbmail@gmail.com","13299999999",new Date());
        //db.save(user);//After the success of the save [not] on the user's primary key assignment assignment
        //db.saveOrUpdate(user);//After the success of the preservation of the user will be the primary key assignment assignment
        //db.saveBindingId(user);
		if(db==null){
			initDb();
		}
        List<ConfigModel> cfigs = new ArrayList<ConfigModel>();
        cfigs.add(new ConfigModel(System.currentTimeMillis(),25, 1));
        db.save(cfigs);
	}
	
	public void dbAdd(int textSize,int type) throws DbException {
		if(db==null){
			initDb();
		}
		
		System.out.println("add....");

        List<ConfigModel> cfigs = new ArrayList<ConfigModel>();
        cfigs.add(new ConfigModel(System.currentTimeMillis(),textSize, type));
        db.save(cfigs);
        
        System.out.println("¡¾dbAdd¡¿the first object:" + cfigs.get(0).toString());

    }
	
	
	public ConfigModel dbFind() throws DbException {
		
		if(db==null){
			initDb();
		}
		
		List<ConfigModel> cfigs = db.selector(ConfigModel.class)
//                .where("name","like","%kevin%")
//                .and("email", "=", "caolbmail@gmail.com")
                .orderBy("id",true)

                .findAll();
		
		for (int i = 0; i < cfigs.size(); i++) {
			System.out.println("******"+cfigs.get(i).toString());
		}
        if(cfigs == null || cfigs.size() == 0){
           dbAdd();
           return dbFind();
        }else{
        	System.out.println(cfigs.get(0).toString());
        	return cfigs.get(0);
        }
	}
	
	
	public List<NoteModel> dbNoteFind() throws DbException {
		
		if(db==null){
			initDb();
		}
		
		List<NoteModel> noteModel = db.selector(NoteModel.class)
//                .where("name","like","%kevin%")
//                .and("email", "=", "caolbmail@gmail.com")
                .orderBy("date",true)
//                .limit(2) /only query two records               .offset(2) 
                .findAll();
		
		System.out.println(noteModel.toString());
		System.out.println("NoteModels find "+noteModel.size());
		
		for (int i = 0; i < noteModel.size(); i++) {
			
			if(noteModel.get(i).getContent()==null||noteModel.get(i).getTitle()==null||
					noteModel.get(i).getDate()==null){
				dbNoteDel(noteModel.get(i).getId());
				noteModel.remove(i);
			}
		}
		
		System.out.println("NoteModels find "+noteModel.size());
		
		System.out.println(noteModel.toString());
		
        return noteModel;
	}
	
	public List<NoteModel> dbNoteFind(int id) throws DbException {
		
		if(db==null){
			initDb();
		}
		
		List<NoteModel> noteModel = db.selector(NoteModel.class)
                .where("id","=",id)
//                .and("email", "=", "caolbmail@gmail.com")
                .orderBy("date",true)
//                .limit(2) //only query two records
//                .offset(2) 
                .findAll();
		
        return noteModel;
	}
	
	
	
	public void dbNoteAdd(String title,String content) throws DbException {
		if(db==null){
			initDb();
		}
		
		System.out.println("add....");

        List<NoteModel> notes = new ArrayList<NoteModel>();
        notes.add(new NoteModel(title,content,new Date()));
        db.save(notes);
        
        System.out.println("¡¾dbNoteAdd¡¿the first object:" + notes.get(0).toString());
//      showDbMessage("¡¾dbAdd¡¿the first object:" + cfigs.get(0).toString());//keyboard of user's id is not 0
//        return flag;
    }
	
	public void dbNoteUpdate(NoteModel noteModel) throws DbException{
		if(db==null){
			initDb();
		}
		
		WhereBuilder whereBuilder = WhereBuilder.b();
        whereBuilder.and("id","=",noteModel.getId());
        db.update(NoteModel.class,whereBuilder,
         new KeyValue("title",noteModel.getTitle())
         ,new KeyValue("content",noteModel.getContent()),
         new KeyValue("date", new Date())
        
        );
	}
	
	public void dbNoteDel(int id) throws DbException{
		
		if(db==null){
			initDb();
		}
		
		WhereBuilder whereBuilder = WhereBuilder.b();
        whereBuilder.and("id","=",id);
        db.delete(NoteModel.class,whereBuilder);
	}
	
	
	
	
	

}
