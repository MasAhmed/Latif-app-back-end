package com.commerce.backend.converter;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.commerce.backend.model.request.userAds.*;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commerce.backend.constants.AdsType;
import com.commerce.backend.dao.ItemObjectCategoryRepository;
import com.commerce.backend.dao.ServiceCategoryRepository;
import com.commerce.backend.model.dto.ItemObjectCategoryVO;
import com.commerce.backend.model.dto.UserAccVO;
import com.commerce.backend.model.dto.UserAdsImageVO;
import com.commerce.backend.model.dto.UserAdsVO;
import com.commerce.backend.model.dto.UserMedicalVO;
import com.commerce.backend.model.dto.UserPetAdsVO;
import com.commerce.backend.model.dto.UserServiceVO;
import com.commerce.backend.model.entity.ItemCategory;
import com.commerce.backend.model.entity.ItemObjectCategory;
import com.commerce.backend.model.entity.MedicalCategory;
import com.commerce.backend.model.entity.PetCategory;
import com.commerce.backend.model.entity.ServiceCategory;
import com.commerce.backend.model.entity.User;
import com.commerce.backend.model.entity.UserAccAds;
import com.commerce.backend.model.entity.UserAds;
import com.commerce.backend.model.entity.UserAdsImage;
import com.commerce.backend.model.entity.UserMedicalAds;
import com.commerce.backend.model.entity.UserPetAds;
import com.commerce.backend.model.entity.UserServiceAds;
import com.commerce.backend.model.request.userAds.UserAdsGeneralAdsRequest;

import java.util.function.Function;

import javax.transaction.Transactional;

@Component
public class UserAdsToVoConverter implements Function< UserAds, UserAdsVO> {
    @Autowired
	ItemObjectCategoryRepository itemObjectCategoryRepository;
    
	@Override
	 public UserAdsVO apply( UserAds source) {
		UserAdsVO userAdsVo = null;
       if(source == null) {
    	   return userAdsVo;
       }
		if(UserAds.class.cast(source).getType() == AdsType.ACCESSORIES) {
			userAdsVo = new UserAccVO();
		}
		else if(UserAds.class.cast(source).getType() == AdsType.PET_CARE){
			userAdsVo = new UserMedicalVO();
		}
		else if(UserAds.class.cast(source).getType() == AdsType.PETS) {
			userAdsVo = new UserPetAdsVO();
		}
		else if(UserAds.class.cast(source).getType() == AdsType.SERVICE) {
			userAdsVo = new UserServiceVO();
		}
		System.out.println("LLL"+UserAds.class.cast(source).getType());
		assert userAdsVo != null;
		this.copyUserAdsEntityToVo(UserAds.class.cast(source), userAdsVo);
		return userAdsVo;
	}

	@Deprecated
	public UserAds transfromVOToEntity(UserAdsVO userAdsVo) {
		UserAds userAds = null;
		if(userAdsVo.getType() == AdsType.ACCESSORIES) {

		}
		else if(userAdsVo.getType() == AdsType.PET_CARE){

		}
		else if(userAdsVo.getType() == AdsType.PETS) {

		}
		else if(userAdsVo.getType() == AdsType.SERVICE) {

		}


		return userAds;
	}


	public UserAds transformRequestToEntity(UserAdsGeneralAdsRequest userAdsRequest) {
		UserAds userAds = null;
		if(userAdsRequest.getType() == AdsType.ACCESSORIES) {
			userAds = new UserAccAds();
		}
		else if(userAdsRequest.getType() == AdsType.PET_CARE){
			userAds = new UserMedicalAds();
		}
		else if(userAdsRequest.getType() == AdsType.PETS) {
			userAds = new UserPetAds();
		}
		else if(userAdsRequest.getType() == AdsType.SERVICE) {
			userAds = new UserServiceAds();
		}
		assert userAds != null;
		return copyUserAdsObject(userAdsRequest, userAds);
	}
    @Deprecated 
	private UserAds copyUserAdsObject(UserAdsGeneralAdsRequest source, UserAds destination) {
		//assert(source.getType() == destination.getType());

		destination.setActive(source.getUserAds().isActive());
		destination.setCode(source.getUserAds().getCode());
		destination.setDescription(source.getUserAds().getDescription());
		destination.setLatitude(source.getUserAds().getLatitude());
		destination.setLongitude(source.getUserAds().getLongitude());
		destination.setName(source.getUserAds().getName());
		destination.setShortDescription(source.getUserAds().getShort_description());
		destination.setPrice(source.getUserAds().getPrice());
		destination.setType(source.getType());
		User user = new User();
		user.setId(source.getUserAds().getCreatedBy());
		destination.setCreatedBy(user);
		destination.setCreatedAt(new Date());
		destination.setUpdatedAt(new Date());
		//--------------------------------------------------------
		if(source.getType() == AdsType.ACCESSORIES) {
		/*	UserAccAdsRequest UserAccAdsRequest = (UserAccAdsRequest) source.getUserAdsRequest();
			((UserAccAds)destination).setUsed(UserAccAdsRequest.getUsed());
			ItemObjectCategory category = new ItemCategory();
			category.setId(UserAccAdsRequest.getCategory_id());
			((UserAccAds)destination).setItemCategoryId((ItemCategory)category);
			category = new ItemCategory();
			category.setId(UserAccAdsRequest.getCategory_type_id());
			((UserAccAds)destination).setItemCategoryType((ItemCategory)category);*/
		}
		if(source.getType() == AdsType.PETS) {
			UserPetsAdsRequest UserPetsAdsRequest = (UserPetsAdsRequest) source.getUserAdsRequest();
			((UserPetAds)destination).setBarkingProblem(UserPetsAdsRequest.getBarkingProblem());
			/*PetCategory category = new PetCategory();
			category.setId(((UserPetsAdsRequest)source.getUserAdsRequest()).getCategory());
			((UserPetAds)destination).setCategory(category);
			category = new PetCategory();
			category.setId(((UserPetsAdsRequest)source.getUserAdsRequest()).getCategoryType());
			((UserPetAds)destination).setPetCategoryType(category);*/
			((UserPetAds)destination).setBreed(((UserPetsAdsRequest)source.getUserAdsRequest()).getBreed());
			((UserPetAds)destination).setBarkingProblem(((UserPetsAdsRequest)source.getUserAdsRequest()).getBarkingProblem());
			//((UserPetAds)destination).setFood(((UserPetsAdsRequest)source.getUserAdsRequest()).getFood());
			((UserPetAds)destination).setDiseasesDisabilities(((UserPetsAdsRequest)source.getUserAdsRequest()).getDiseasesDisabilities());;
			((UserPetAds)destination).setDiseasesDisabilitiesDesc(((UserPetsAdsRequest)source.getUserAdsRequest()).getDiseasesDisabilitiesDesc());
			((UserPetAds)destination).setNeutering(((UserPetsAdsRequest)source.getUserAdsRequest()).getNeutering());
			//((UserPetAds)destination).setTraining(UserPetsAdsRequest.getTraining());
			((UserPetAds)destination).setPlayWithKids(UserPetsAdsRequest.getPlayWithKids());
			((UserPetAds)destination).setPassport(UserPetsAdsRequest.getPassport());
		    ((UserPetAds)destination).setVaccinationCertifcate((UserPetsAdsRequest.getVaccinationCertificate()));;
			//((UserPetAds)destination).setImage(UserPetsAdsRequest.getImage().getName());


		}
		else if(source.getType() == AdsType.PET_CARE) {
			((UserMedicalAds)destination).setAllowServiceAtHome(((UserMedicalAdsRequest) source.getUserAdsRequest()).getAvaliable_at_home());
		}
		else if(source.getType() == AdsType.SERVICE) {
		/*	UserServiceAdsRequest UserServiceAdsRequest = (UserServiceAdsRequest) source.getUserAdsRequest();
			((UserServiceAds)destination).setAllowServiceAtHome(UserServiceAdsRequest.getAvaliable_at_home());
			ServiceCategory serviceCategory = new ServiceCategory();
			serviceCategory.setId((UserServiceAdsRequest.getCategory_id()));
				((UserServiceAds)destination).setServiceCategory(serviceCategory);
			UserServiceAdsRequest.setCategory_type_id(UserServiceAdsRequest.getCategory_type_id());*/
		//	((UserServiceAds)destination).setServiceCategory(serviceCategory);
	//		source.getUserServiceAdsRequest().setCategory_type_id(source.getUserServiceAdsRequest().getCategory_type_id());

		}
		return destination;
	}
 
	public UserAdsVO copyUserAdsEntityToVo(UserAds source, UserAdsVO destination) {
		//assert(source.getType() == destination.getType());
		
		destination.setId(source.getId());
		destination.setActive(source.isActive());
		destination.setCode(source.getCode());
		destination.setDescription(source.getDescription());
		destination.setLatitude(source.getLatitude());
		destination.setLongitude(source.getLongitude());
		destination.setName(source.getName());
		destination.setShort_description(source.getShortDescription());
		destination.setPrice(source.getPrice());
		destination.setExternal_link(source.getExternalLink());
		 Set<UserAdsImage> images = source.getUserAdsImage();
		if(images != null) {
			//Hibernate.initialize(images);
			 Set<UserAdsImageVO> imageVos = new HashSet<UserAdsImageVO>();
			 images.stream().forEach(img ->{
				 UserAdsImageVO imgVo = new UserAdsImageVO();
				 imgVo.setId(img.getId());
				 imgVo.setImage(img.getImage());
				 imgVo.setExternal_link(source.getExternalLink());
				 imgVo.setUserAdsId(source.getId());
			     imageVos.add(imgVo);
			 });
          
   	      destination.setImages(imageVos);
		 }
		User user = new User();
		if(source.getCreatedBy() != null) {
			user.setId(source.getCreatedBy().getId());
			user.setFirstName(source.getCreatedBy().getFirstName());
			user.setLastName(source.getCreatedBy().getLastName());
			user.setAvatar(source.getCreatedBy().getAvatar());
			user.setPhone(source.getCreatedBy().getPhone());
		}
		destination.setCreatedBy(user);
		destination.setCreated_at(source.getCreatedAt());
		destination.setUpdated_at(source.getUpdatedAt());
		destination.setType(source.getType());
		if(source.getType() == AdsType.PETS || source.getType() == AdsType.Dogs) {
			((UserPetAdsVO)destination).setBarkingProblem(((UserPetAds)source).getBarkingProblem());
			//ItemObjectCategoryVO itemObjectCategoryVO = new ItemObjectCategoryVO(((UserPetAds)source).getCategory());
			//((UserPetAdsVO)destination).setCategory(itemObjectCategoryVO);
			((UserPetAdsVO)destination).setBreed(((UserPetAds)source).getBreed());
			((UserPetAdsVO)destination).setBarkingProblem(((UserPetAds)source).getBarkingProblem());
			((UserPetAdsVO)destination).setFood(((UserPetAds)source).getFood());
			((UserPetAdsVO)destination).setDiseasesDisabilities(((UserPetAds)source).getDiseasesDisabilities());;
			((UserPetAdsVO)destination).setDiseasesDisabilitiesDesc(((UserPetAds)source).getDiseasesDisabilitiesDesc());
			((UserPetAdsVO)destination).setNeutering(((UserPetAds)source).getNeutering());
			((UserPetAdsVO)destination).setTraining(((UserPetAds)source).getTraining());
			((UserPetAdsVO)destination).setPlayWithKids(((UserPetAds)source).getPlayWithKids());
			((UserPetAdsVO)destination).setPassport(((UserPetAds)source).getPassport());
		    ((UserPetAdsVO)destination).setVaccinationCertificate(((UserPetAds)source).getVaccinationCertifcate());;
		    ((UserPetAdsVO)destination).setWeaned(((UserPetAds)source).getWeaned());
		    ((UserPetAdsVO)destination).setStock(((UserPetAdsVO)destination).getStock());
		    if(((UserPetAdsVO)destination).getImages() != null){
		    	Optional<UserAdsImageVO> adsImage = ((UserPetAdsVO)destination).getImages().stream().findFirst();
		    	if(adsImage.isPresent()) {
		    	((UserPetAdsVO)destination).setImage(adsImage.get().getImage());
		    	}
		    
		    }
		    PetCategory category = ((UserPetAds)source).getCategory();
		    String categoryName = category == null ?null:category.getName();
		    if(categoryName != null) {
		    ((UserPetAdsVO)destination).setCategoryName(categoryName);
		    ((UserPetAdsVO)destination).setCategoryNameAr(category.getNameAr());
		    ((UserPetAdsVO)destination).setCategoryId(category.getId());
		    }
		}
		if(source.getType() == AdsType.SERVICE) {
			ServiceCategory category = ((UserServiceAds)source).getServiceCategory();
			 //ItemObjectCategory orgCategory = this.itemObjectCategoryRepository.findById(category.getId()).orElse(null);
		
			if(category != null) {
			((UserServiceVO)destination).setAllowAtHome(((UserServiceAds)source).getAllowServiceAtHome());
			((UserServiceVO)destination).setCategoryId(category.getId());
			((UserServiceVO)destination).setCategoryName(category.getName());
			((UserServiceVO)destination).setCategoryNameAr(category.getNameAr());
			}
		}
		else if(source.getType() == AdsType.PET_CARE) {
			((UserMedicalVO)destination).setAllowServiceAtHome(((UserMedicalAds)source).getAllowServiceAtHome());
			MedicalCategory category = ((UserMedicalAds)source).getMedicalCategoryType();
			if(category != null) {
			((UserMedicalVO)destination).setAllowServiceAtHome(((UserServiceAds)source).getAllowServiceAtHome());
			((UserMedicalVO)destination).setCategoryId(category.getId());
			((UserMedicalVO)destination).setCategoryName(category.getName());
			((UserMedicalVO)destination).setCategoryNameAr(category.getNameAr());
			}
		}
		else if(source.getType() == AdsType.ACCESSORIES) {
			((UserAccVO)destination).setUsed(((UserAccAds)source).getUsed());
		    ItemCategory itemCategory = ((UserAccAds)source).getItemCategoryId();
		    if(itemCategory != null) {
			((UserAccVO)destination).setCategoryId(itemCategory.getId());
			((UserAccVO)destination).setCategoryName(itemCategory.getNameAr());
			
			((UserAccVO)destination).setStock(((UserAccAds)source).getStock());
			
			ItemCategory category = ((UserAccAds)source).getItemCategoryId();
			if(category != null) {
			((UserAccVO)destination).setCategoryId(category.getId());
			((UserAccVO)destination).setCategoryName(category.getName());
			((UserAccVO)destination).setCategoryNameAr(category.getNameAr());
			}
		    }
		}
		else if(source.getType() == AdsType.DELIVERY) {
			
		}
		 
		return destination;
	}
}
