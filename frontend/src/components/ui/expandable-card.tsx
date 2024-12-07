import React, { useState } from "react";
import { motion } from "framer-motion";
import { IconMail, IconCalendar, IconUser } from "@tabler/icons-react";

interface Email {
  id: string;
  subject: string;
  from: string;
  date: string;
  body: string;
}

export const ExpandableCard = ({ email }: { email: Email }) => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <motion.div
      animate={isOpen ? "open" : "closed"}
      className={`w-full border-2 border-gray-200 rounded-lg overflow-hidden ${
        isOpen ? "bg-white" : "bg-gray-50 hover:bg-gray-100"
      } cursor-pointer transition-colors`}
      onClick={() => setIsOpen(!isOpen)}
    >
      <div className="p-4">
        <div className="flex items-start justify-between">
          <div className="flex items-center space-x-2">
            <IconMail className="w-5 h-5 text-blue-500" />
            <h3 className="text-lg font-semibold text-gray-900">{email.subject}</h3>
          </div>
          <motion.div
            variants={{
              open: { rotate: 180 },
              closed: { rotate: 0 },
            }}
            transition={{ duration: 0.2 }}
            className="origin-center"
          >
            <svg
              className="w-4 h-4 text-gray-500"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M19 9l-7 7-7-7"
              />
            </svg>
          </motion.div>
        </div>

        <div className="mt-2 flex items-center space-x-4 text-sm text-gray-500">
          <div className="flex items-center">
            <IconUser className="w-4 h-4 mr-1" />
            <span>{email.from}</span>
          </div>
          <div className="flex items-center">
            <IconCalendar className="w-4 h-4 mr-1" />
            <span>{new Date(email.date).toLocaleDateString()}</span>
          </div>
        </div>

        <motion.div
          variants={{
            open: { height: "auto", opacity: 1 },
            closed: { height: 0, opacity: 0 },
          }}
          transition={{ duration: 0.2 }}
          className="overflow-hidden"
        >
          <div className="pt-4 text-gray-600">{email.body}</div>
        </motion.div>
      </div>
    </motion.div>
  );
};
