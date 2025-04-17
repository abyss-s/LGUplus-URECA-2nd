import type { Metadata } from "next";
import { Inter } from "next/font/google";
import QueryProvider from "@/components/common/QueryProvider";
const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "query test ",
  description: "Generated by create next app",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <QueryProvider>
      <div>{children}</div>
    </QueryProvider>
  );
}
