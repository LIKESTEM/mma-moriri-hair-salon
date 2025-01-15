/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const view_appointment = document.getElementById('view_appointment');
const view_update = document.getElementById('view_update');
const view_reschedule = document.getElementById('view_reschedule');

const list_appointments = document.getElementById('list_appointments');
const update_details = document.getElementById('update_details');
const rescedule_appointment = document.getElementById('rescedule_appointment');

function showAppointments(){
    update_details.classList.add('dont_show');
    rescedule_appointment.classList.add('dont_show');
    list_appointments.classList.remove('dont_show');
    
    view_appointment.classList.add('dindicate_selected');
    view_update.classList.remove('dindicate_selected');
    view_reschedule.classList.remove('dindicate_selected');
}

function showSchedule(){

    list_appointments.classList.add('dont_show');
    update_details.classList.add('dont_show');
    rescedule_appointment.classList.remove('dont_show');
    
    view_reschedule.classList.add('dindicate_selected');
    view_appointment.classList.remove('dindicate_selected');
    view_update.classList.remove('dindicate_selected');
    
}

function showUpdate(){

    list_appointments.classList.add('dont_show');
    rescedule_appointment.classList.add('dont_show');
    update_details.classList.remove('dont_show');
    
    view_update.classList.add('dindicate_selected');
        view_appointment.classList.remove('dindicate_selected');
        view_reschedule.classList.remove('dindicate_selected');
    
}

view_appointment.addEventListener('click', showAppointments);
view_reschedule.addEventListener('click', showSchedule);
view_update.addEventListener('click', showUpdate);
indicate_selected





