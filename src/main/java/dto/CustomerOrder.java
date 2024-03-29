package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	private LocalDateTime orderDate;
	private double totalPrice;
	private LocalDateTime deliveryTime;
	@OneToMany
	List<CustomerFoodItem> foodItems;
}
