import { useEffect, useState } from "react";
import { ExpandableCard } from "../components/ui/expandable-card";
import { IconRefresh } from "@tabler/icons-react";

interface Email {
  id: string;
  subject: string;
  from: string;
  date: string;
  body: string;
}

export default function Home() {
  const [emails, setEmails] = useState<Email[]>([]);
  const [loading, setLoading] = useState(false);

  const fetchEmails = async () => {
    setLoading(true);
    try {
      const response = await fetch("http://localhost:8080/api/emails/new");
      const data = await response.json();
      setEmails(data);
    } catch (error) {
      console.error("Error fetching emails:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEmails();
  }, []);

  return (
    <main className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-100 py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-3xl mx-auto">
        <div className="flex justify-between items-center mb-8">
          <h1 className="text-3xl font-bold text-gray-900">Email Notifications</h1>
          <button
            onClick={fetchEmails}
            disabled={loading}
            className="flex items-center space-x-2 px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors disabled:opacity-50"
          >
            <IconRefresh className={`w-5 h-5 ${loading ? "animate-spin" : ""}`} />
            <span>Refresh</span>
          </button>
        </div>

        <div className="space-y-4">
          {emails.length === 0 ? (
            <div className="text-center py-12 bg-white rounded-lg shadow">
              <p className="text-gray-500">No new emails</p>
            </div>
          ) : (
            emails.map((email) => (
              <ExpandableCard key={email.id} email={email} />
            ))
          )}
        </div>
      </div>
    </main>
  );
}
