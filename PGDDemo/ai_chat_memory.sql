
CREATE TABLE spring_ai_chat_memory (
	conversation_id varchar(36) NOT NULL,
	"content" text NOT NULL,
	"type" varchar(10) NOT NULL,
	"timestamp" timestamp NOT NULL,
	sequence_id int8 NOT NULL,
	CONSTRAINT spring_ai_chat_memory_type_check CHECK (((type)::text = ANY ((ARRAY['USER'::character varying, 'ASSISTANT'::character varying, 'SYSTEM'::character varying, 'TOOL'::character varying])::text[])))
);
CREATE INDEX spring_ai_chat_memory_conversation_id_sequence_id_idx ON public.spring_ai_chat_memory USING btree (conversation_id, sequence_id);
CREATE INDEX spring_ai_chat_memory_conversation_id_timestamp_idx ON public.spring_ai_chat_memory USING btree (conversation_id, "timestamp");