package io.spring.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.model.District;
import io.spring.model.Province;
import io.spring.model.Ward;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonUtil {
    public List<Province> getProvince() throws IOException {
        File file = new File("E:\\Java\\Spring\\vietnam-provinces-0.4.2\\vietnam_provinces\\data\\nested-divisions.json");

        ObjectMapper objectMapper = new ObjectMapper();

        List<Province> provinces = new ArrayList<>();

        JsonNode jsonNode = objectMapper.readTree(file);

        for (JsonNode item : jsonNode) {
            Province province = new Province();
            province.setName(item.get("name").textValue());
            province.setCode(Integer.parseInt(item.get("code").toString()));
            province.setCodename(item.get("codename").textValue());
            province.setDivision_type(item.get("division_type").textValue());
            province.setPhone_code(Integer.parseInt(item.get("phone_code").toString()));
            JsonNode c_node = item.get("districts");
            List<District> districts = new ArrayList<>();
            for (JsonNode c_item : c_node) {
                District district = new District();
                district.setName(c_item.get("name").textValue());
                district.setCode(Integer.parseInt(c_item.get("code").toString()));
                district.setCodename(c_item.get("codename").textValue());
                district.setDivision_type(c_item.get("division_type").textValue());
                district.setShort_codename(c_item.get("short_codename").textValue());
                List<Ward> wards = new ArrayList<>();
                JsonNode cc_node = c_item.get("wards");
                for(JsonNode cc_item: cc_node){
                    Ward ward = new Ward();
                    ward.setName(cc_item.get("name").textValue());
                    ward.setCode(Integer.parseInt(cc_item.get("code").toString()));
                    ward.setCodename(cc_item.get("codename").textValue());
                    ward.setDivision_type(cc_item.get("division_type").textValue());
                    ward.setShort_codename(cc_item.get("short_codename").textValue());
                    wards.add(ward);
                }
                district.setWards(wards);
                districts.add(district);
            }
            province.setDistricts(districts);
            provinces.add(province);
        }

       return provinces;
    }

    public String getAddress(int province, int district, int ward) throws IOException {
        StringBuilder sb = new StringBuilder();

        List<Province> provinces = getProvince();
        for(Province p_item: provinces){
            if(p_item.getCode() == province){
                sb.append(p_item.getName());
                List<District> districts = p_item.getDistricts();
                for(District d_item: districts){
                    if(d_item.getCode() == district){
                        sb.insert(0, d_item.getName() + ", ");
                        List<Ward> wards = d_item.getWards();
                        for(Ward w_item: wards){
                            if(w_item.getCode() == ward){
                                sb.insert(0,w_item.getName()  + ", ");
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}
