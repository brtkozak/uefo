create schema IF NOT EXISTS apigateway;
create schema IF NOT EXISTS atendees;
create schema IF NOT EXISTS matches;
create schema IF NOT EXISTS notifications;
create schema IF NOT EXISTS orders;
create schema IF NOT EXISTS payments;


/*
 Api Gateway
*/
create table if not exists atendee (
    id BIGINT,
    pesel VARCHAR(11),
    start_date  DATE,
    end_date DATE);


/*
 Attendees
*/

/*
 Matches
*/


/*
 Notifications
*/


/*
Orders
*/

/*
 Payments
*/