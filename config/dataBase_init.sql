CREATE TABLE resume
(
    uuid      CHAR(36) PRIMARY KEY NOT NULL,
    full_name TEXT                      NOT NULL
);

CREATE TABLE public.contact
(
    id          SERIAL,
    CONSTRAINT resume_uuid_fk FOREIGN KEY (resume_uuid)
        REFERENCES resume (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    value       TEXT     NOT NULL,
    type        TEXT     NOT NULL,
    resume_uuid CHAR(36) NOT NULL
)