package com.clawson.restful.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Event {
    private @Id @GeneratedValue Long id;
    private String eventName;
    private String eventDescription;
    private String eventDate;
    private String eventPlatform; //Where the event originally was posted
    private String eventPrice;
    private boolean isFree;
    private String address;
    
    //private Organizer organizer; //Basically doesn't know how to store this field in a database
    //So I have to figure out what it would be.

    public Event() {}

    /**
     * @param eventName
     * @param eventDescription
     * @param eventDate
     * @param eventPlatform
     * @param eventPrice
     * @param isFree
     * @param address
     */
    public Event(String eventName, String eventDescription, String eventDate, String eventPlatform, String eventPrice,
            boolean isFree, String address) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventPlatform = eventPlatform;
        this.eventPrice = eventPrice;
        this.isFree = isFree;
        this.address = address;
    }

    /**
     * @param eventName
     * @param eventDescription
     * @param eventDate
     * @param eventPlatform
     * @param eventPrice
     * @param isFree
     * @param address
     * @param organizer
     */
    // public Event(String eventName, String eventDescription, String eventDate, String eventPlatform, String eventPrice,
    //         boolean isFree, String address, Organizer organizer) {
    //     this.eventName = eventName;
    //     this.eventDescription = eventDescription;
    //     this.eventDate = eventDate;
    //     this.eventPlatform = eventPlatform;
    //     this.eventPrice = eventPrice;
    //     this.isFree = isFree;
    //     this.address = address;
    //     this.organizer = organizer;
    // }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName the eventName to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * @return the eventDescription
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * @param eventDescription the eventDescription to set
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * @return the eventDate
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the eventPlatform
     */
    public String getEventPlatform() {
        return eventPlatform;
    }

    /**
     * @param eventPlatform the eventPlatform to set
     */
    public void setEventPlatform(String eventPlatform) {
        this.eventPlatform = eventPlatform;
    }

    /**
     * @return the eventPrice
     */
    public String getEventPrice() {
        return eventPrice;
    }

    /**
     * @param eventPrice the eventPrice to set
     */
    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    /**
     * @return the isFree
     */
    public boolean isFree() {
        return isFree;
    }

    /**
     * @param isFree the isFree to set
     */
    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the organizer
     */
    // public Organizer getOrganizer() {
    //     return organizer;
    // }

    // /**
    //  * @param organizer the organizer to set
    //  */
    // public void setOrganizer(Organizer organizer) {
    //     this.organizer = organizer;
    // }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
        result = prime * result + ((eventDescription == null) ? 0 : eventDescription.hashCode());
        result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
        result = prime * result + ((eventPlatform == null) ? 0 : eventPlatform.hashCode());
        result = prime * result + ((eventPrice == null) ? 0 : eventPrice.hashCode());
        result = prime * result + (isFree ? 1231 : 1237);
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        //result = prime * result + ((organizer == null) ? 0 : organizer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (eventName == null) {
            if (other.eventName != null)
                return false;
        } else if (!eventName.equals(other.eventName))
            return false;
        if (eventDescription == null) {
            if (other.eventDescription != null)
                return false;
        } else if (!eventDescription.equals(other.eventDescription))
            return false;
        if (eventDate == null) {
            if (other.eventDate != null)
                return false;
        } else if (!eventDate.equals(other.eventDate))
            return false;
        if (eventPlatform == null) {
            if (other.eventPlatform != null)
                return false;
        } else if (!eventPlatform.equals(other.eventPlatform))
            return false;
        if (eventPrice == null) {
            if (other.eventPrice != null)
                return false;
        } else if (!eventPrice.equals(other.eventPrice))
            return false;
        if (isFree != other.isFree)
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        // if (organizer == null) {
        //     if (other.organizer != null)
        //         return false;
        // } else if (!organizer.equals(other.organizer))
        //     return false;
        return true;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", eventName=" + eventName + ", eventDescription=" + eventDescription
                + ", eventDate=" + eventDate + ", eventPlatform=" + eventPlatform + ", eventPrice=" + eventPrice
                + ", isFree=" + isFree + ", address=" + address + ", organizer=" + "organizer" + "]";
    }


}
