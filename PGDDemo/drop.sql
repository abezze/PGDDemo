
    set client_min_messages = WARNING;

    alter table if exists appuntamento 
       drop constraint if exists FKw0v0mkrxb4oamr38xm51imtv;

    alter table if exists appuntamento 
       drop constraint if exists FKalxcwgpdmivy7p7llwvyaemgc;

    alter table if exists associativautenteprofilo 
       drop constraint if exists FKlk1oaftqb8jugv2rmiglabamp;

    alter table if exists associativautenteprofilo 
       drop constraint if exists FK7vubn1vyoas8mtjw9ofv28le6;

    alter table if exists delega 
       drop constraint if exists FK1mmeaobkuv245chn7shhb5ih1;

    alter table if exists delega 
       drop constraint if exists FKcrflwniqlgnkl0dp0fx978j6k;

    alter table if exists delega 
       drop constraint if exists FK73olidakub711s9184wopu2f2;

    alter table if exists storicodelega 
       drop constraint if exists FK67ity6goblm8ljes857uvxv0n;

    alter table if exists storicodelega 
       drop constraint if exists FK8ms0lq591ijw4mykqo7ldh1j1;

    alter table if exists storicodelega 
       drop constraint if exists FK70hhpmfcvaa21k0xy4wbw87pu;

    alter table if exists storicostatoappuntamento 
       drop constraint if exists FKkjktlcopfy328futpx56s7qf0;

    alter table if exists storicostatoappuntamento 
       drop constraint if exists FKb772eke5pg8vp6ri976392ye9;

    alter table if exists storicostatoappuntamento 
       drop constraint if exists FKjbxxcyy354ves2htqdqou22sl;

    drop table if exists appuntamento cascade;

    drop table if exists associativautenteprofilo cascade;

    drop table if exists delega cascade;

    drop table if exists profilo cascade;

    drop table if exists scenario cascade;

    drop table if exists statoappuntamento cascade;

    drop table if exists statodelega cascade;

    drop table if exists storicodelega cascade;

    drop table if exists storicostatoappuntamento cascade;

    drop table if exists utente cascade;
