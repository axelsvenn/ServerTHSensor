package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entities.Device;
import entities.Notification;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repo.DeviceRepository;
import repo.NotificationRepository;



@RestController
public class AppController extends HttpServlet {
	@Autowired
	DeviceRepository deviceRepo;
	
	@Autowired
	NotificationRepository notificationRepo;
	
	@GetMapping("/device")
	public List<Device> getDevices(@Nullable @RequestParam("id") Long id) {
		ArrayList<Device> arrayList = new ArrayList<>();
		
		if (id == null) arrayList.addAll(deviceRepo.findAll());
		else deviceRepo.getReferenceById(id);
		
		return arrayList;
	}
	
	@PutMapping("/device")
	public void saveDevice(@RequestBody Device myDevice) {
		deviceRepo.save(myDevice);
	}
	
	@DeleteMapping("/device")
	public void deleteDevice(@RequestBody Long id) {
		deviceRepo.deleteById(id);
	}
	
	@GetMapping("/notification")
	public List<Notification> getNotifications(@Nullable @RequestParam("id") Long id) {
		ArrayList<Notification> arrayList = new ArrayList<>();
		
		if (id == null) arrayList.addAll(notificationRepo.findAll());
		else notificationRepo.getReferenceById(id);
		
		return arrayList;
	}
	
	@PutMapping("/notification")
	public void saveNotification(@RequestBody Notification notification) {
		notificationRepo.save(notification);
	}
	
	
	@DeleteMapping("/notification/{id}")
	public void deleteNotification(@PathVariable Long id) {
		System.out.println(id);
		notificationRepo.deleteById(id);
	}
	
	@GetMapping("/notification/{id}")
	public Set<Notification> getDeviceNotifications(@PathVariable("id") Long device_id) {
		return deviceRepo.getReferenceById(device_id).getNotifications();
	}
}
