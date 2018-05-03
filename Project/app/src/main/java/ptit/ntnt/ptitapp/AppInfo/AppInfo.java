package ptit.ntnt.ptitapp.AppInfo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Database.DBHelper;
import ptit.ntnt.ptitapp.R;

/**
 * Created by vdkhoa on 3/23/18.
 */

public class AppInfo extends android.support.v4.app.Fragment {
    View view;
    DBHelper database;
    TextView haha;
    ExpandableListView expandListView;
    List<String> listAppInfoHeader;
    HashMap<String, List<String>> listAppInfoChild;
    CustomAppInfo customAppInfo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.app_info_layout,container,false);
        database = new DBHelper(getActivity());
        database.insertDev(124,"Nguyễn Quốc Đạt");
        database.insertDev(46,"Văng Đăng Khoa");
        database.insertDev(87,"Phan Thị Ngọc Tuyền");
        database.insertDev(22,"Nguyễn Vũ Đức Anh");
        database.insertDev(93,"Lê Văn Khuê");
        database.insertDev(8,"Dương Quang Khang");
        database.insertDev(97,"Nguyễn Quang Tuấn Lộc");
        database.insertDev(45,"Vũ Đăng Tâm");
        database.insertDev(19,"Phạm Hoàng Việt Khánh");
        database.insertDev(18,"Hoàng Anh Minh");
        database.insertDev(14,"Nguyễn Phước Duy");

        database.insertMission(1,"Database");
        database.insertMission(2,"Activity Tin tức");
        database.insertMission(3,"Thay đổi TKB, ghi chú TKB");
        database.insertMission(4,"Design layout");
        database.insertMission(5,"Top-level Activity");
        database.insertMission(6,"Form đăng nhập");
        database.insertMission(7,"Quên mật khẩu");
        database.insertMission(8,"Đăng xuất");
        database.insertMission(9,"Dialog");
        database.insertMission(10,"Đổi mật khẩu");
        database.insertMission(11,"Hướng dẫn sử dụng");
        database.insertMission(12,"Thư góp ý");
        database.insertMission(13,"Cài đặt ứng dụng");
        database.insertMission(14,"Xem học phí");
        database.insertMission(15,"Xem điểm theo học kì");
        database.insertMission(16,"Xem điểm (tất cả)");
        database.insertMission(17,"Xem lịch thi");
        database.insertMission(18,"Thông tin ứng dụng");
        database.insertMission(19,"Xem TKB theo ngày");
        database.insertMission(20,"Xem TKB theo tuần");
        database.insertMission(21,"Thay đổi thông tin các nhân");
        database.insertMission(22,"Xem khung đào tạo");
        database.insertMission(23,"Xem thông tin giáo viên, đánh giá giáo viên");
        database.insertMission(24,"Xem các môn đã đăng ký");
        database.insertMission(25,"Notification");

        database.insertTonghop(1,124);
        database.insertTonghop(2,124);
        database.insertTonghop(3,124);
        database.insertTonghop(4,46);
        database.insertTonghop(5,46);
        database.insertTonghop(6,87);
        database.insertTonghop(7,87);
        database.insertTonghop(8,87);
        database.insertTonghop(9,22);
        database.insertTonghop(10,22);
        database.insertTonghop(11,93);
        database.insertTonghop(12,93);
        database.insertTonghop(13,8);
        database.insertTonghop(14,8);
        database.insertTonghop(15,97);
        database.insertTonghop(16,97);
        database.insertTonghop(17,45);
        database.insertTonghop(18,45);
        database.insertTonghop(19,19);
        database.insertTonghop(20,18);
        database.insertTonghop(21,19);
        database.insertTonghop(22,18);
        database.insertTonghop(23,14);
        database.insertTonghop(24,14);
        database.insertTonghop(25,22);
        database.insertTonghop(3,46);
        haha = (TextView) view.findViewById(R.id.haha);
        expandListView = (ExpandableListView) view.findViewById(R.id.AppInfoLV);
        listAppInfoHeader = new ArrayList<String>();
        AddControl();
//        AddControlphu();
        customAppInfo = new CustomAppInfo(AppInfo.this.getActivity(), listAppInfoHeader, listAppInfoChild);
        expandListView.setAdapter(customAppInfo);
        return view;
    }
    public void AddControlphu(){
        String s = "";
        Cursor getData;
        getData = database.GetData("Select DEV_NAME from TB_DEV order by DEV_ID asc");
        while (getData.moveToNext()){
            s+= getData.getString(0)+"    ";
        }
        haha.setText(s);
    }
    public void AddControl() {
        String s = "";
        int count = 0;
        int dem;
        Cursor DataSV;
        DataSV = database.GetData("SELECT DEV_NAME FROM TB_DEV order by DEV_ID asc");
        listAppInfoHeader.clear();
        while (DataSV.moveToNext()) {
            String tenSV = DataSV.getString(0);
            listAppInfoHeader.add(tenSV);
        }

        listAppInfoChild = new HashMap<String, List<String>>();
        Cursor DataChild = database.GetData("select A.DEV_ID, A.DEV_NAME, TB_MISSION.MISSION_NAME from (select TB_DEV.DEV_ID, TB_DEV.DEV_NAME, TB_TONGHOP.MISSION_ID from TB_DEV join TB_TONGHOP on TB_DEV.DEV_ID = TB_TONGHOP.DEV_ID) as A join TB_MISSION on A.MISSION_ID = TB_MISSION.MISSION_ID ORDER BY A.DEV_ID ASC");
        Cursor CountNhiemVu = database.GetData("select DEV_ID, count(B.DEV_ID) as SoNhiemVu from (select A.DEV_ID,A.DEV_NAME, TB_MISSION.MISSION_NAME from (select TB_DEV.DEV_ID, TB_DEV.DEV_NAME, TB_TONGHOP.MISSION_ID from TB_DEV join TB_TONGHOP on TB_DEV.DEV_ID = TB_TONGHOP.DEV_ID) as A join TB_MISSION on A.MISSION_ID = TB_MISSION.MISSION_ID) as B group by DEV_ID order by DEV_ID asc");
        Cursor DataTest = database.GetData("select * from TB_DEV join TB_TONGHOP on TB_DEV.DEV_ID = TB_TONGHOP.DEV_ID");
//        DataChild.moveToFirst();
//        while(DataTest.moveToNext()){
//            s+=DataTest.getString(0)+" "+DataTest.getString(1)+" "+DataTest.getString(2)+" "+DataTest.getString(3) +"  " ;
//            haha.setText(s);
//        }
        ArrayList<Integer> countNV = new ArrayList<Integer>();
        while (CountNhiemVu.moveToNext()) {
            countNV.add(CountNhiemVu.getInt(1));
        }
        s="";
        for (int i = 0; i < countNV.size(); i++) {
            s += countNV.get(i) + "   ";
        }
        for (int i = 0; i < countNV.size(); i++) {
            List<String> nhiemvu = new ArrayList<>();
            nhiemvu.clear();
            dem = 0;
            while (DataChild.moveToNext()) {
                nhiemvu.add(DataChild.getString(2));
                if (DataChild.isLast()) break;
                dem++;
                if (dem == countNV.get(i)) break;
            }
            listAppInfoChild.put(listAppInfoHeader.get(i), nhiemvu);
        }
    }
}